/*
 * Jaio: Java API for libaio
 *
 * Author:
 * Jonas Pfefferle <jpf@zurich.ibm.com>
 *
 * Copyright (C) 2016, IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

#include <com_ibm_jaio_NativeDispatcher.h>

#include <cerrno>
#include <cstring>
#include <cstdlib>
#include <fcntl.h>
#include <unistd.h>
#include <iostream>
#include <chrono>
#include <libaio.h>

jint throwException(JNIEnv *jenv, int err) {
    jclass clazz = jenv->FindClass("java/io/IOException");
    if (clazz == NULL) {
        std::cerr << "Could not find class!\n";
        std::exit(-1);
    }
    return jenv->ThrowNew(clazz, strerror(err < 0 ? -err : err));
}

/*
 * Class:     com_ibm_jaio_NativeDispatcher
 * Method:    io_setup
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_com_ibm_jaio_NativeDispatcher_io_1setup
  (JNIEnv *jenv, jclass, jint nrEvents) {
    io_context_t ctx = NULL;
    int ret = io_setup(nrEvents, &ctx);
    if (ret) {
        throwException(jenv, ret);
    }
    return (jlong)ctx;
}

/*
 * Class:     com_ibm_jaio_NativeDispatcher
 * Method:    io_submit
 * Signature: (JJJ)V
 */
JNIEXPORT jint JNICALL Java_com_ibm_jaio_NativeDispatcher_io_1submit
  (JNIEnv *jenv, jclass, jlong ctx, jlong nr, jlong iocbpp) {
    iocb **ios = reinterpret_cast<iocb **>(iocbpp);
    int ret = io_submit((io_context_t)ctx, nr, ios);
    if (ret < 0) {
        return throwException(jenv, ret);
    }
    return ret;
}

/*
 * Class:     com_ibm_jaio_NativeDispatcher
 * Method:    io_getevents
 * Signature: (JJJJ)I
 */
JNIEXPORT jint JNICALL Java_com_ibm_jaio_NativeDispatcher_io_1getevents
  (JNIEnv *jenv, jclass, jlong ctx, jlong min, jlong nr, jlong events,
   jlong milli_seconds) {
    io_event *native_events = (io_event *)events;
    int ret;
    if (milli_seconds < 0) {
        ret = io_getevents((io_context_t)ctx, min, nr, native_events, NULL);
    } else {
        using namespace std::chrono;
        milliseconds ms{ milli_seconds };
        seconds sec = duration_cast<seconds>(ms);
        timespec ts = { sec.count(),
            duration_cast<nanoseconds>(ms - sec).count()};
        ret = io_getevents((io_context_t)ctx, min, nr, native_events, &ts);
    }
    if (ret < 0) {
        return throwException(jenv, ret);
    }
    return ret;
}

/*
 * Class:     com_ibm_jaio_NativeDispatcher
 * Method:    io_destroy
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_ibm_jaio_NativeDispatcher_io_1destroy
  (JNIEnv *jenv, jclass, jlong ctx) {
    int ret = io_destroy((io_context_t)ctx);
    if (ret) {
        throwException(jenv, ret);
    }
}

/*
 * Class:     com_ibm_jaio_NativeDispatcher
 * Method:    open
 * Signature: (Ljava/lang/String;I)I
 */
JNIEXPORT jint JNICALL Java_com_ibm_jaio_NativeDispatcher_open
  (JNIEnv *jenv, jclass, jstring path, jint flags) {
      const char *native_path = jenv->GetStringUTFChars(path, 0);
      int ret = open(native_path, flags);
      jenv->ReleaseStringUTFChars(path, native_path);
      if (ret < 0) {
          throwException(jenv, ret);
      }
      return ret;
}

/*
 * Class:     com_ibm_jaio_NativeDispatcher
 * Method:    close
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_ibm_jaio_NativeDispatcher_close
  (JNIEnv *jenv, jclass, jint fd) {
    int ret = close(fd);
    if (ret) {
        throwException(jenv, ret);
    }
}


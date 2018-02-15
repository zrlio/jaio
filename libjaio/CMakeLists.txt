cmake_minimum_required(VERSION 2.8)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)
project (libjaio)


find_package(JNI)
include_directories(. ${JNI_INCLUDE_DIRS})
link_libraries(aio)
add_compile_options("-std=c++11")
add_compile_options("-Wall")
add_compile_options("-Werror")

add_library(jaio SHARED com_ibm_jaio_NativeDispatcher.cpp)
set_target_properties(jaio PROPERTIES SOVERSION "0.2")
install(TARGETS jaio LIBRARY DESTINATION lib)


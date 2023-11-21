add_library(sut sut.cpp)
target_include_directories(sut PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
add_executable(main main.cpp)
target_link_libraries(main sut)

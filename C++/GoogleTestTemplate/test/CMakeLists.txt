# Example for using FetchContent to get Google Test
############################################################
# FetchContent_Declare(
#   googletest
#   GIT_REPOSITORY https://github.com/google/googletest.git
#   GIT_TAG        release-1.11.0
# )
# FetchContent_MakeAvailable(googletest)
# add_library(GTest::GTest INTERFACE IMPORTED)
# target_link_libraries(GTest::GTest INTERFACE gtest_main)

set(TEST_NAME runTests)

find_package(GTest REQUIRED)
include_directories(${GTEST_INCLUDE_DIRS})

add_executable(${TEST_NAME} testsuite.cpp)
target_link_libraries(${TEST_NAME} src ${GTEST_LIBRARIES})

add_test(NAME ${TEST_NAME} COMMAND ${TEST_NAME})

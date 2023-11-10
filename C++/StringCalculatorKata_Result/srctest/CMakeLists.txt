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

find_package(GTest REQUIRED)

add_executable(StringCalculatorTest StringCalculatorTest.cpp)

target_link_libraries(StringCalculatorTest
 PRIVATE
  GTest::GTest
  StringCalculator)

add_test(StringCalculatorTest StringCalculatorTest)
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

add_executable(GildedRoseTest GildedRoseTest.cpp)

target_link_libraries(GildedRoseTest
 PRIVATE
  GTest::GTest
  GildedRose)

add_test(GildedRoseTest GildedRoseTest)
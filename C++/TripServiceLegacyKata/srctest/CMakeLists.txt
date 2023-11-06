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

add_executable(TripServiceTest TripServiceTestAdapter.cpp TripServiceTest.cpp)

target_link_libraries(TripServiceTest
 PRIVATE
  GTest::GTest
  TripService)

add_test(TripServiceTest TripServiceTest)
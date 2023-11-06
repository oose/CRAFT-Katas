cmake_minimum_required(VERSION 3.16)
project(RomanNumeralsKata LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

enable_testing()

find_package(GTest REQUIRED)

add_executable(Converter ArabicToRomanNumeralsConverterTestCase.cpp)

target_link_libraries(Converter
 PRIVATE
  GTest::GTest)

add_test(Converter Converter)
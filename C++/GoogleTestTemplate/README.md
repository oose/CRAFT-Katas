# Setting Up Google Test
How to get Google Test up and running. Requires cmake which can be installed with `sudo apt-get install cmake`.

## Either install packages and compile...
```
sudo apt-get install libgtest-dev
cd /usr/src/gtest
mkdir build && cd build
sudo cmake ..
sudo make
```

## ...or download from GitHub and compile
```
git clone https://github.com/google/googletest.git
cd googletest/googletest
mkdir build && cd build
cmake ..
make
```

## Copy or symlink libraries to your /usr/local/lib folder
```
sudo cp *.a /usr/local/lib
```

## Example for CMakeLists.txt
```
cmake_minimum_required(VERSION 2.6)
 
# Locate GTest
find_package(GTest REQUIRED)
include_directories(${GTEST_INCLUDE_DIRS})
 
# Link runTests with what we want to test and the GTest and pthread library
add_executable(runTests tests.cpp)
target_link_libraries(runTests ${GTEST_LIBRARIES} pthread)
```

## Google Test Primer
https://github.com/google/googletest/blob/master/googletest/docs/Primer.md

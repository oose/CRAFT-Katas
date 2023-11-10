set(TEST_NAME runTests)

add_executable(${TEST_NAME} testsuite.cpp)
target_link_libraries(${TEST_NAME} src)

add_test(NAME ${TEST_NAME} COMMAND ${TEST_NAME})

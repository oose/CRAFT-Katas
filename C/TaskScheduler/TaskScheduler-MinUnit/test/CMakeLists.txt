add_executable(config_test_app
    config_test.c
)

target_link_libraries(config_test_app
    scheduler
    config
)

add_test(config_test config_test_app)

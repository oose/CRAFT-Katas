add_executable(main
    main.c
)

target_include_directories(main PUBLIC ../scheduler)

target_link_libraries(main
    scheduler
    config
)


add_executable(main
    main.c
)

target_include_directories(main PUBLIC ../GildedRose)

target_link_libraries(main
    GildedRose
)


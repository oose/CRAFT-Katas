project("Observer Pattern" C)
cmake_minimum_required(VERSION 3.0)


add_executable(main
    main.c TimeSource.c Watch.c
)

target_link_libraries(main
    pthread
)

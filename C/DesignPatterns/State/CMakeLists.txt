project("State Pattern" C)
cmake_minimum_required(VERSION 3.0)


add_executable(main
    main.c WatchState.c RunningState.c StoppedState.c StopWatch.c
)

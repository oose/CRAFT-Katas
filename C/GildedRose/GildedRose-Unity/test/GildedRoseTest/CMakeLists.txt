add_executable(GildedRoseTest_App
    GildedRoseTest.c
)

target_include_directories(GildedRoseTest_App PUBLIC ../../GildedRose)

target_link_libraries(GildedRoseTest_App
    GildedRose
    Unity
)

add_test(GildedRoseTest GildedRoseTest_App)
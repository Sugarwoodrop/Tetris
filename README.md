Вторая лабораторная НГУ ФИТ на языке Java.

Постановка задачи:

Написать аналог игры «Тетрис» (“Tetris”). Набор фигур стандартный – все возможные
вариации связных многоугольников, составленных из 4-х квадратов. Архитектура
программы должна быть основана на паттерне MVC (Mode-View-Controller).

Требования к программе:

1. Игра должна поддерживать таблицу рекордов.
2. Пользователю должны быть доступны команды: Exit, About, New Game, High Scores.
Методические указания:
Шаблон проектирования “MVC”:
o http://rsdn.ru/article/patterns/generic-mvc.xml
o http://ru.wikipedia.org/wiki/Model-View-Controller
Для реализации пользовательского интерфейса использовать библиотеку Swing
(классы из пакета javax.swing.*).
Работа с компонентами пользовательского интерфейса (классами библиотеки Swing)
должна проходить только из UI потока.
Для отображения диалоговых окон рекомендуется использовать класс JOptionPane.


Реализация:

Сделал Tetris с классическим набором фигур и паттерном MVC, реализация поворотов на основе старой SRS(Super Rotation System) с восьмью вариантами поворотов.
Графика реализована при помощи библиотеки swing. Статистика игры(никнейм и счет) записываются в отдельный файл .json для дальнейшего просмотра таблицы рекордов. 
Все основные константы хранятся в отдельном классе Setting.

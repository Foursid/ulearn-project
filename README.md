# Итоговый проект по Java

## Подготовка

#### 1. Добавлены необходимые зависимости:

![dependency](https://github.com/Foursid/ulearn-project/blob/master/Images/dependency.png) <br> <br>

#### 2. Создан POJO класс на основе данных из csv файла:

![pojo](https://github.com/Foursid/ulearn-project/blob/master/Images/CountryInfoPOJO.png) <br> <br>

#### 3. Написан парсер для чтения csv файла и добавлен config, в котором хранятся данные для подключения:

![internal](https://github.com/Foursid/ulearn-project/blob/master/Images/Internal.png)  


## Подключение к базе данных sqlite

#### 1. Создано подключение и отключение от базы данных:

![connection](https://github.com/Foursid/ulearn-project/blob/master/Images/Connection.png) 
![disconnect](https://github.com/Foursid/ulearn-project/blob/master/Images/Disconnect.png) <br> <br>

#### 2. Реализовано создание таблиц и их заполнение данными из csv файла:

Создание таблицы Country

![country-table](https://github.com/Foursid/ulearn-project/blob/master/Images/CountryTable.png) <br>

Заполнение таблицы Country

![fill-country](https://github.com/Foursid/ulearn-project/blob/master/Images/FillDataCountryTable.png) <br> <br>

Создание таблицы Population

![population-table](https://github.com/Foursid/ulearn-project/blob/master/Images/PopulationTable.png) <br>

Заполнение таблицы Population

![fill-population](https://github.com/Foursid/ulearn-project/blob/master/Images/FillDataPopulationTable.png) <br> <br>

## Задачи по данным из csv файла

#### 1. Сформируйте график по показателю здоровья объеденив их по странам

Запрос в базу данных:

![sql1](https://github.com/Foursid/ulearn-project/blob/master/Images/1_sql.png)

Полученная гистограмма:

![histogram](https://github.com/Foursid/ulearn-project/blob/master/Images/Histogram.png)

#### 2. Выведите в консоль средний показатель здоровья среди Western Europe и Sub-Saharan Africa

Запрос в базу данных:

![sql2](https://github.com/Foursid/ulearn-project/blob/master/Images/2_sql.png)

Ответ:

![ans2](https://github.com/Foursid/ulearn-project/blob/master/Images/Answer2.png)

#### 3. Найдите страну с "самыми средними показателями" среди Western Europe и Sub-Saharan Africa

Запрос в базу данных:

![sql3](https://github.com/Foursid/ulearn-project/blob/master/Images/3_sql.png)

Ответ:

![ans3](https://github.com/Foursid/ulearn-project/blob/master/Images/Answer3.png)

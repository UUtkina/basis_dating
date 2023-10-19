--В рамках БД music напишите след/запросы:
--Вывести имена юзеров не из France
db.users.find(
    { country: { $ne: "France" } },
    { country: 1, fullname: 1, _id: 0 }
)
--Добавить несколько реакций на треки
--Вывести названия треков продолжительностью от 1 до 10 мин
db.tracks.find(
    { duration_secs: { $gte:1*60, $lte:10*60} },
    { duration_secs: 1, title: 1, _id: 0 }
)
--Вывести треки юзера 4
db.tracks.find(
    { author_id:14 },
    { duration_secs: 1, title: 1, _id: 0 })
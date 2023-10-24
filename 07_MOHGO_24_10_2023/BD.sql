--В рамках БД music напишите след/запросы:
--Вывести названия двух произвольных треков с тегом new
db.tracks.aggregate([
    { $match:{  tags: 'new'}},{ $sample: { size: 2 }}
    
])


--Вывести имя юзера с самым минимальным балансом

db.users.aggregate([
    {$sort: { balance: 1 } },
    { $limit: 1 
    },
    { $project: {
            _id: 0,
            fullname: 1,
            balance: 1
        }
    }
])

--Вывести имена юзеров, у которых есть треки

db.users.aggregate([
    {
        $lookup: {
            from: 'tracks',
            localField: '_id',
            foreignField: 'author_id',
            as: 'tracks'
        }
    },
    {
        $match: {
            tracks: { $ne: [] } // Фильтруем только пользователей, у которых есть треки
        }
    },
    {
        $project: {
            _id: 0,
            fullname: 1 
        }
    }
])
--Используя метод aggregate(), вывести ко-во юзеров с балансом до 1000 EUR
db.users.aggregate([
    { $match: { balance: { $lt:1000 } } },
    { $count:"numberUsers"}
])
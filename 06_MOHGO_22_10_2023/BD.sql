--В рамках БД music напишите след/запросы:
--Вывести названия треков без тега test
db.tracks.find({ tags: { $ne: "test" } }, { duration_secs: 1, title: 1, _id: 0 })

--Вывести ко-во треков с продолжительностью до одного часа с тегом new
db.tracks.countDocuments({ tags: "new", duration_secs:{$lte: 3600} })

--Увеличить баланс заблокированных юзеров не из Germany на 5%
db.users.updateMany(
    { country: { $ne: "Germany" }, is_blocked:true  },
    {
      $mul: { balance: 1.05 } 
    }
)

--Добавить всем трекам теги fresh и popular
db.tracks.updateMany(
    {},
    {
        $push: {
            tags:{$each: ['fresh', 'popular']}
        }
    }
)
--В рамках БД music напишите след/запросы:
--Вывести среднюю реакцию по треку 1
db.reactions.aggregate([
  {
    $match: { track_id: ObjectId("652faa6b5bc707bb7f6def50") } },
  { $group: {
      _id: '$track_id', 
      avg_value: { $avg: '$value' } 
    }
  }
])



--Увеличить баланс всех незаблокированных юзеров на 0.5%

db.users.updateMany(
    {  is_blocked:{$ne: true } },
    {
      $mul: { balance: 1.05 } 
    }
)


--Вывести ко-во реакций с оценкой 4

db.reactions.aggregate([
    { $match: { value: 4 } },
    { $count:"number"}
])

--Вывести названия треков, принадлежащих юзеру 4
db.tracks.aggregate([
  {
    $match: {
      author_id: 4 
    }
  },
  {
    $project: {
      _id: 0, 
      track_title: '$title' 
    }
  }
])
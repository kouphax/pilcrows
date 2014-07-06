package models


case class Quote(id: Long, quote: String, attributedTo: String, year: Int)


object Quote {

  import anorm._
  import play.api.db.DB
  import play.api.Play.current

  def rowToQuote(row: Row) = Quote(
    row[Long]("id"),
    row[String]("quote"),
    row[String]("attributed_to"),
    row[Int]("year")
  )

  def selectAll = {
    DB.withConnection { implicit connection =>
      SQL"SELECT * FROM quotes".map(rowToQuote).list()
    }
  }

  def selectSingle(id: Long) = {
    DB.withConnection { implicit connection =>
      SQL"SELECT * FROM quotes WHERE id = $id".map(rowToQuote).singleOpt()
    }
  }
}
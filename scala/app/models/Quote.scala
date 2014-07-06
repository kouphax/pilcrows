package models


case class Quote(id: Long, quote: String, attributedTo: String, year: Int)


object Quote {

  import anorm._
  import play.api.db.DB
  import play.api.Play.current
  import play.api.data._
  import play.api.data.Forms._

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

  def update(id: Long, newQuote: Quote) {
    DB.withConnection { implicit connection =>
      SQL"""UPDATE quotes
            SET quote = ${newQuote.quote},
                attributed_to = ${newQuote.attributedTo},
                year = ${newQuote.year}
            WHERE id = $id""".execute()
    }
  }

  def save(quote: Quote) {
    DB.withConnection { implicit connection =>
      SQL"""INSERT INTO quotes(quote, attributed_to, year)
            VALUES (${quote.quote}, ${quote.attributedTo}, ${quote.year})""".execute()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit connection =>
      SQL"DELETE FROM quotes WHERE id = $id".execute()
    }
  }

  def search(query: String) = {
    DB.withConnection { implicit connection =>
      val like = s"%$query%"
      SQL"SELECT * FROM quotes WHERE quote LIKE $like".map(rowToQuote).list()
    }
  }

  val form = Form(
    mapping(
      "id" -> ignored(0L),
      "quote" -> nonEmptyText,
      "attributedTo" -> nonEmptyText,
      "year" -> number
    )(Quote.apply)(Quote.unapply)
  )
}
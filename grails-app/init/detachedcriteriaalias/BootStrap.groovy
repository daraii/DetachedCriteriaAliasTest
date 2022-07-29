package detachedcriteriaalias

class BootStrap {

    def init = { servletContext ->
        Author.withTransaction {
            new Author(name: "J.K. Rowling")
                    .addToBooks(new Book(title: "Harry Potter and the Philosopher's Stone")
                            .addToIsbn(new Isbn(pubGroup: "0", publisher: "7475", title: "3274", checkDigit: "5")))
                    .save(failOnError: true)
        }

        Publisher.withTransaction {
            new Publisher(name: "Bloomsbury Publishing PLC", pubIdentifier: "7475")
                    .save(failOnError: true)
        }
    }
    def destroy = {
    }
}

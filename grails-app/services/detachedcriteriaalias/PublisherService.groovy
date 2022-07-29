package detachedcriteriaalias

import grails.gorm.DetachedCriteria
import grails.gorm.transactions.Transactional

@Transactional
class PublisherService {

    def getAuthorsPublishers(Author a) {

        def subquery = new DetachedCriteria<Isbn>(Isbn).build{
            createAlias("book", "b")
            projections {
                property("publisher")
            }
            'eq'("b.author", a)
        }

        def x = Publisher.createCriteria().list {
            'in'("pubIdentifier", subquery)
        }

//        Publisher.createCriteria().list {
//            'in'("pubIdentifier", new DetachedCriteria<Book>(Book).build{
//                createAlias("isbn", "i")
//                projections {
//                    property("i.publisher")
//                }
//                'eq'("author", a)
//            })
//        }
    }
}
package detachedcriteriaalias

import grails.gorm.services.Service

@Service(Isbn)
interface IsbnService {

    Isbn get(Serializable id)

    List<Isbn> list(Map args)

    Long count()

    void delete(Serializable id)

    Isbn save(Isbn isbn)

}
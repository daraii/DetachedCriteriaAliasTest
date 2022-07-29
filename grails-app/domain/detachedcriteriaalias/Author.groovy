package detachedcriteriaalias

import groovy.transform.ToString

@ToString(includePackage = false, includes = ['name'])
class Author {
    static hasMany = [books: Book]

    String name
}

package detachedcriteriaalias

import groovy.transform.ToString

@ToString(includePackage = false, includes = ['title'])
class Book {
    String title

    static belongsTo = [author: Author]
    static hasMany = [isbn: Isbn]
}
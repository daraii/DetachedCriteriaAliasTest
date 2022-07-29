package detachedcriteriaalias

import groovy.transform.ToString

@ToString(includePackage = false, includes = ['pubGroup', 'publisher', 'title', 'checkDigit'])
class Isbn {
    String pubGroup
    String publisher
    String title
    String checkDigit

    static belongsTo = [book: Book]
    static constraints = {
    }
}

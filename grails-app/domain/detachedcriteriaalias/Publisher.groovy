package detachedcriteriaalias

import groovy.transform.ToString

@ToString(includePackage = false, includes = ['name', 'pubIdentifier'])
class Publisher {
    String name
    String pubIdentifier

    static constraints = {
    }
}

package detachedcriteriaalias

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class IsbnController {

    IsbnService isbnService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond isbnService.list(params), model:[isbnCount: isbnService.count()]
    }

    def show(Long id) {
        respond isbnService.get(id)
    }

    def create() {
        respond new Isbn(params)
    }

    def save(Isbn isbn) {
        if (isbn == null) {
            notFound()
            return
        }

        try {
            isbnService.save(isbn)
        } catch (ValidationException e) {
            respond isbn.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'isbn.label', default: 'Isbn'), isbn.id])
                redirect isbn
            }
            '*' { respond isbn, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond isbnService.get(id)
    }

    def update(Isbn isbn) {
        if (isbn == null) {
            notFound()
            return
        }

        try {
            isbnService.save(isbn)
        } catch (ValidationException e) {
            respond isbn.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'isbn.label', default: 'Isbn'), isbn.id])
                redirect isbn
            }
            '*'{ respond isbn, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        isbnService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'isbn.label', default: 'Isbn'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'isbn.label', default: 'Isbn'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

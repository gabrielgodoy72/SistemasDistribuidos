package com.sd

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured

class SecureController {

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() {
        if(SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')) {
            render(view:'/index')
        }

    }
}

package com.prospring.ch4.config

import com.prospring.ch4.name.Singer
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader
import org.springframework.context.support.GenericApplicationContext

def ctx = new GenericApplicationContext()
def reader = new GroovyBeanDefinitionReader(ctx)

reader.beans {
    singer(Singer, name: 'John', age: 25)
}

ctx.refresh()
println ctx.getBean("singer")
package leatcode

import spock.lang.Specification

class PrintFooBarAlternately1115Test extends Specification {

    def "check in 2 threads"() {
        expect:
        def foo = new PrintFoo()
        def bar = new PrintBar()
        final PrintFooBarAlternately1115.FooBar fooBar = new PrintFooBarAlternately1115.FooBar(50)
        def first = new Thread(new Runnable() {
            @Override
            void run() {
                fooBar.foo(foo)
            }
        })
        def second = new Thread(new Runnable() {
            @Override
            void run() {
                fooBar.bar(bar)
            }
        })
        first.start()
        second.start()
        first.join()
        second.join()
    }

    class PrintFoo implements Runnable {

        @Override
        void run() {
            print("foo")
        }
    }

    class PrintBar implements Runnable {

        @Override
        void run() {
            print("bar")
        }
    }
}

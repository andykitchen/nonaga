(defproject nonaga "0.1.0-SNAPSHOT"
  :description "A Clojure implementation of the game Spanish table top game
               Nonaga."
  :url "https://github.com/logaan/nonaga"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2138"]
                 [com.cemerick/clojurescript.test "0.2.1"]
                 [com.cemerick/piggieback "0.1.2"]]
  :plugins [[lein-cljsbuild "1.0.1"]
            [com.cemerick/clojurescript.test "0.2.1"]]
  :externs ["externs/react.js"]
  :hooks  [leiningen.cljsbuild]
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  :cljsbuild {:builds {:dev {:source-paths ["src"]
                             :compiler {:output-to "resources/js/main.js"
                                        :optimizations :whitespace
                                        :pretty-print true}}
                       :test {:source-paths ["src" "test"]
                              :compiler {:output-to "resources/js/test.js"
                                         :optimizations :whitespace
                                         :pretty-print true}}}
              :test-commands {"unit" ["phantomjs" :runner 
                                      "window.literal_js_was_evaluated=true"
                                      "resources/js/test.js"]}})


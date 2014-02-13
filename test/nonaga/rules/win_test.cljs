(ns nonaga.rules.win-test
  (:require-macros [cemerick.cljs.test :refer (deftest is are)])
  (:require [nonaga.rules.win :as w]
            [nonaga.core :as c]
            [cemerick.cljs.test :as t]))

(def blue-wins
  {:rings (:rings c/initial-game)

   :red
    #{   [1 4]

                             [4 2]

         [1 0]}

   :blue
    #{
       [0 3]
     [0 2]
       [0 1]
     }
   })

(def red-wins
  {:rings (:rings c/initial-game)

   :red
    #{
                          [3 3]
                             [4 2]
                          [3 1]
     }

   :blue
    #{               [3 4]

     [0 2]

                     [3 0]}
   })

(def blue-wins-line
  {:rings (:rings c/initial-game)

   :red
    #{   [1 4]

                             [4 2]

         [1 0]}

   :blue
    #{

           [1 2] [2 2] [3 2]

     }
   })

(deftest wins
  (is (= (w/find-winner c/initial-game) nil))
  (is (= (w/find-winner blue-wins) :blue))
  (is (= (w/find-winner red-wins) :red))
  (is (= (w/find-winner blue-wins-line) :blue)))

(ns tic-tac-toe.player-creator
  (:require [fungp.core :as fungp]
            [fungp.util :as fungp.util]
            [tic-tac-toe.core :as ttt])
  (:use clojure.pprint))

(def functions
  "Here's a vector of vectors consisting of [symbol arity] pairs. The symbol must resolve
   to a function, and the arity number is an integer representing how many arguments
   that function takes."
  '[[+ 2]
    [- 2]
    [* 2]
    ])

(def training-range
  "This defines the range of input to use as training input."
  (range -5 6))


(defn match-func [a]
  (* a a))

(def actual-output
  "This defines the actual outputs we are trying to match."
  (map float (map match-func training-range)))

(def terminals ['a])

(defn calculate-error-score [tree]
  (let [results (map (fungp.util/compile-tree tree terminals) training-range)]
    (reduce + (map fungp.util/off-by-sq actual-output results))))

(defn sample-report
  "Reporting function. Prints out the tree and its score"
  [tree fitness]
  (pprint tree)
  (println (str "Error:\t" fitness "\n"))
  (flush))


(defn test-genetic-program [iteration-count migration-count]
  (println "\nfungp :: Functional Genetic Programming in Clojure")
  (println (str "Test inputs: " (vec training-range)))
  (println (str "Test outputs: " (vec actual-output)))
  (println (str "Max generations: " (* iteration-count migration-count)))
  (println)
  (let [[tree score]
        (rest (fungp/run-genetic-programming {:iterations           iteration-count
                                              :migrations           migration-count
                                              :num-islands          6
                                              :population-size      40
                                              :tournament-size      5
                                              :mutation-probability 0.1
                                              :max-depth            10
                                              :terminals            terminals
                                              :numbers              (map float (range 11))
                                              :fitness              (fn [] (try calculate-error-score (catch Exception _ 999999)))
                                              :functions            functions
                                              :report               sample-report}))]
    (do (println "Done!")
        (sample-report tree score))))




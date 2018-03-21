(ns fungp-example  
  (:use fungp.core) ;; include the core framework
  (:use fungp.util) ;; include utility functions
  (:use clojure.pprint))

(def sample-functions
  "Array of [symbol arity] pairs. The symbol must resolve to a function
   and the arity number is an integer representing how many arguments
   that function takes."
  '[
    [* 2]    
    [< 2]
    [> 2]
    [if 3]
    [fungp.util/abs 1]
    [fungp.util/sdiv 2]
    [+ 2]
    [- 2]
    [true? 1]
    ])

(def training-range  
  (range -10 11))

(defn match-func [x]
  (if (< x 0)
    (* 0 x)
    (* x x)))

(def target
  "This defines the actual outputs we are trying to match."
  (map float (map match-func training-range)))

(def sample-parameters
  ['a])

(defn calculate-error-score [tree]
  (try
    (let [f (compile-tree tree sample-parameters)
          results (map f training-range)] ;; map the function to the test range
      
      (reduce + (map off-by-sq target results)))
    (catch Exception e
      #_(println "Error in tree" tree)
      999999999)))

(defn sample-report
  "Reporting function. Prints out the tree and its score"
  [tree fitness]
  (pprint tree)
  (println (str "Error:\t" fitness "\n"))
  (flush))

(defn test-genetic-program [iteration-count migration-count]
  (println "\n" "fungp :: Functional Genetic Programming in Clojure")
  (println (str "Test inputs: " (vec training-range)))
  (println (str "Test outputs: " (vec target)))
  (println (str "Max generations: " (* iteration-count migration-count)))
  (println  "Results are printed to the screen at each migration.") 
  (println)
  (let [number-literals (map float (range 10))        
        options {:iterations iteration-count :migrations migration-count :num-islands 6 :population-size 45
                 :tournament-size 10 :mutation-probability 0.35
                 :max-depth 10 :terminals sample-parameters
                 :numbers number-literals :fitness calculate-error-score
                 :functions sample-functions :report sample-report }
        ;; the data returned by run-genetic-programming is as follows:
        ;; [population [best-tree score]]
        ;; we save off the tree and score like this        
        [tree score] (rest (run-genetic-programming options))]
    (do (println "Done!")
        (sample-report tree score))))

;;;     user=> (use 'fungp.tutorial)
;;;     nil
;;;     user=> (test-genetic-program 15 15)
;;;     ...
;;;

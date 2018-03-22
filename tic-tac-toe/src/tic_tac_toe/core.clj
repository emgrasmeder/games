(ns tic-tac-toe.core
  (:gen-class))

(def upper-left 1)
(def upper-center 2)
(def upper-right 3)
(def center-left 4)
(def center-center 5)
(def center-right 6)
(def bottom-left 7)
(def bottom-center 8)
(def bottom-right 9)

(def base-board [nil nil nil
                 nil nil nil
                 nil nil nil])

(defn make-move [old-matrix marker location]
  (assoc old-matrix (-  location 1) marker))

(defn get-moves [board]
  (filter #(not (nil? %))
          (map (fn [n i] (if (nil? n) (+ 1 i) nil))
               board (range (count board)) )))

(defn has-diagonal-winner? [board]
  (and (not (= base-board board))
       (or (= (get board 1)
              (get board 5)
              (get board 9))
           (= (get board 3)
              (get board 5)
              (get board 7)))))

(defn has-horizontal-winner? [board]
  (and (not (= base-board board))
       (or (= (get board 1)
              (get board 2)
              (get board 3))
           (= (get board 4)
              (get board 5)
              (get board 6))
           (= (get board 7)
              (get board 8)
              (get board 9))))  )

(defn has-vertical-winner? [board]
  (and (not (= base-board board))
       (or (= (get board 1)
              (get board 4)
              (get board 7))
           (= (get board 2)
              (get board 5)
              (get board 8))
           (= (get board 3)
              (get board 6)
              (get board 9)))))

(defn has-winner? [board]
  (or (has-diagonal-winner? board)
      (has-vertical-winner? board)
      (has-horizontal-winner? board)))

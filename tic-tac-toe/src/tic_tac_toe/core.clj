(ns tic-tac-toe.core
  (:gen-class))

(def upper-left :00)
(def upper-center :10)
(def upper-right :20)
(def center-left :01)
(def center-center :11)
(def center-right :21)
(def bottom-left :02)
(def bottom-center :12)
(def bottom-right :22)

(def base-board {upper-left nil upper-center nil upper-right nil
                 center-left nil center-center nil center-right nil
                 bottom-left nil bottom-center nil bottom-right nil})

(defn make-move [old-matrix marker x y]
  (assoc old-matrix (keyword (reduce str [x y])) marker))

#_(defn get-moves-in-row [r]
    (for [i r]
      (filter #(not (nil? %))
              (map (fn [i] (if (nil? (nth r i))
                             i
                             nil))
                   r))))

(defn get-moves [board]
  (keys (into {} (filter (comp nil? val) board))))

(defn has-diagonal-winner? [board]
  (or (= (upper-left board)
         (center-center board)
         (bottom-right board))
      (= (upper-right board)
         (center-center board)
         (bottom-left board))))

(defn has-horizontal-winner? [board]
  (or (= (upper-left board)
         (upper-center board)
         (upper-right board))
      (= (center-left board)
         (center-center board)
         (center-right board))
      (= (bottom-left board)
         (bottom-right board)
         (bottom-right board))))

(defn has-vertical-winner? [board]
  (or (= (upper-left board)
         (center-left board)
         (bottom-left board))
      (= (upper-center board)
         (center-center board)
         (bottom-center board))
      (= (upper-right board)
         (center-right board)
         (bottom-right board))))

(defn has-winner? [board]
  (or (has-diagonal-winner? board)
      (has-vertical-winner? board)
      (has-horizontal-winner? board)))

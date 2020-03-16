package main 

trait List[A] {
  def filter(f: A => Boolean): List[A]
}



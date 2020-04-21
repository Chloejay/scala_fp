package abstraction


trait List[A] {
    def h_o_f(f: A => Int): List[A]
}

case object typeClass {
    def apply[A](a: A) = a
}

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(ab: A => B): F[B]
}
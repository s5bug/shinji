package tf.bug.shinji
package instances

case class MonoidFunction1[A, B](
  mndA: Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, A],
  mndB: Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, B],
  f: A => B
)

object MonoidScal extends Category[Any, Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, *], MonoidFunction1] {

  override def id[A <: Any](v: Monoid[Any, λ[α => Unit], Function, Unit, Tuple2, A]): MonoidFunction1[A, A] =
    MonoidFunction1(v, v, Scal.id[A](()))

  override def compose[A <: Any, B <: Any, C <: Any](f: MonoidFunction1[A, B], g: MonoidFunction1[B, C]): MonoidFunction1[A, C] =
    MonoidFunction1(f.mndA, g.mndB, Scal.compose(f.f, g.f))

  override def homExtractLeft[A <: Any, B <: Any](hom: MonoidFunction1[A, B]): Monoid[Any, λ[α => Unit], Function, Unit, Tuple2, A] =
    hom.mndA

  override def homExtractRight[A <: Any, B <: Any](hom: MonoidFunction1[A, B]): Monoid[Any, λ[α => Unit], Function, Unit, Tuple2, B] =
    hom.mndB

  def liftMonad[F[_ <: Any] <: Any](from: Monad[Any, λ[α => Unit], Function1, F]): Monad[Any, Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, *], MonoidFunction1, F] =
    new Monad[Any, Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, *], MonoidFunction1, F] {
      override val endofunctorCategory: Category[Any, Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, *], MonoidFunction1] = MonoidScal

      override def mapVal[A <: Any](v: Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, A]): Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, F[A]] =
        new Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, F[A]] {
          override val monoidalCategory: MonoidalCategory[Any, λ[α => Unit], Function1, Unit, Tuple2] = Scal

          override def empty: Unit => F[A] =
            (x: Unit) => from.pure(())(v.empty(x))

          override def combine: ((F[A], F[A])) => F[A] = {
            case (l, r) =>
              val joinA = (x: A) => from.map((y: A) => v.combine((x, y)))
              val lr = from.map((x: A) => joinA(x)(r))(l)
              from.flatten(())(lr)
          }
        }

      override def map[A <: Any, B <: Any](f: MonoidFunction1[A, B]): MonoidFunction1[F[A], F[B]] =
        MonoidFunction1(mapVal(f.mndA), mapVal(f.mndB), from.map(f.f))

      override def pure[A <: Any](v: Monoid[Any, λ[α => Unit], Function, Unit, Tuple2, A]): MonoidFunction1[A, F[A]] =
        MonoidFunction1(v, mapVal(v), from.pure(()))

      override def flatten[A <: Any](v: Monoid[Any, λ[α => Unit], Function, Unit, Tuple2, A]): MonoidFunction1[F[F[A]], F[A]] =
        MonoidFunction1(mapVal(mapVal(v)), mapVal(v), from.flatten(()))
    }

}

object ListTAdjunction extends Adjunction[
  /* ObjE = */ Any,
  /* ValE = */ λ[α => Unit],
  /* HomE = */ Function1,
  /* ObjI = */ Any,
  /* ValI = */ Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, *],
  /* HomI = */ MonoidFunction1,
  /* U = */ List,
  /* F = */ λ[α => α],
] {

  override def free: Functor[Any, λ[α => Unit], Function1, Any, Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, *], MonoidFunction1, List] =
    new Functor[Any, λ[α => Unit], Function1, Any, Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, *], MonoidFunction1, List] {
      override val functorCategoryIn: Category[Any, λ[α => Unit], Function1] = Scal
      override val functorCategoryOut: Category[Any, Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, *], MonoidFunction1] = MonoidScal

      override def mapVal[A <: Any](v: Unit): Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, List[A]] =
        new Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, List[A]] {
          override val monoidalCategory: MonoidalCategory[Any, λ[α => Unit], Function, Unit, Tuple2] = Scal

          override def empty: Unit => List[A] = _ => List.empty[A]

          override def combine: ((List[A], List[A])) => List[A] = { case (l, r) => l ++ r }
        }

      override def map[A <: Any, B <: Any](f: A => B): MonoidFunction1[List[A], List[B]] =
        MonoidFunction1(mapVal[A](()), mapVal[B](()), _.map(f))
    }

  override def forgetful: Functor[Any, Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, *], MonoidFunction1, Any, λ[α => Unit], Function1, λ[α => α]] =
    new Functor[Any, Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, *], MonoidFunction1, Any, λ[α => Unit], Function1, λ[α => α]] {
      override val functorCategoryIn: Category[Any, Monoid[Any, λ[α => Unit], Function, Unit, Tuple2, *], MonoidFunction1] = MonoidScal
      override val functorCategoryOut: Category[Any, λ[α => Unit], Function1] = Scal

      override def mapVal[A <: Any](v: Monoid[Any, λ[α => Unit], Function, Unit, Tuple2, A]): Unit = ()

      override def map[A <: Any, B <: Any](f: MonoidFunction1[A, B]): A => B = f.f
    }

  override def iso[A <: Any, B <: Any](va: Unit, vb: Monoid[Any, λ[α => Unit], Function1, Unit, Tuple2, B]): Iso[Any, λ[α => Unit], Function1, MonoidFunction1[List[A], B], A => B] =
    Iso[Any, λ[α => Unit], Function1, MonoidFunction1[List[A], B], A => B](
      forward = (f: MonoidFunction1[List[A], B]) => (x: A) => f.f(List(x)),
      backward = (f: A => B) => {
        MonoidFunction1[List[A], B](free.mapVal[A](()), vb, (x: List[A]) => {
          x.map(f).fold(vb.empty(()))((a, b) => vb.combine((a, b)))
        })
      }
    )
}

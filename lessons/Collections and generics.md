# Collections and generics

## Introduction

I learnt today something useful when thinking about generics, collections and abstraction.

I had a problem in my project where I have a grid (kinda like a board game). In this board,
I have Cell that depending on the game, can have different states.

These states, depends on the game I am playing at the moment. So I Thought that this is the
only piece on my project that need to change depending on the game, everything else has kinda the same
struct.

On the code, this grid was represented with the type "Cell[][]". Now, after thinking, I needed the cells to be
generic in function of the game.  like:

Cell<TState>

With TState changing by the game it will instantiate them.

I will detail my problem on a List (haha, spoiler)

## Analysis

1. I tried to instantiate this 2D array Cell<TState>[][].

I learnt that first, generics dissapear at runtime (make sense), because the concrete types are the one that are actually
instantiated at runtime.

But the arrays has the particularity that they do the type check at runtime. So, since they don't have a type to verify with,
they dont allow to declare generic type arrays[].

✅ List<List<Cell<TState>>> works because generics are checked only at compile time.
❌ new Cell<TState>[size][size] fails because arrays need to know their element type at runtime.

2. On the other hand, I tried then changing to List<List<Cell<TState>>> and something happened.

While I tried to initialize my grid as

List<List<Cell<TState>>> grid = new ArrayList<ArrayList<Cell<TState>>>()

the compiler refused me to do so. I didn't see the problem since this seemed ok:

List<List<Cell<TState>>> grid = new ArrayList<List<Cell<TState>>>()

**UNDERSTANDING**

### What is a subtype?

"A subtype refers to the relationship between types where one type can safely be used
anywhere another type is expected"

That means it accomplish the Liskov Substitution principle:

If S is a subtype of T, then objects of type T may be replaced with objects of type S
without altering any of the desirable properties of the program.

### Problem explanation

The problem relies on the missmatch between the declaration of grid and the object I am trying to assign.
I declare grid as a List of List<Cell>, but assigned an ArrayList of ArrayList<Cell>.
An ArrayList<E> it implements List<E>, but E has to exactly match.

Now, ArrayList<Cell> it is a subtype of List<Cell> 
but ArrayList<ArrayList<Cell>> is not a subtype of List<List<Cell>>. 

BECAUSE <List<Cell>> can be a LinkedList<Cell> or an ArrayList<Cell>, etc
THEREFORE having List<List<Cell>> grid = new ArrayList<ArrayList<Cell> implies at the left
that 

grid.add(new LinkedList<Cell>)
grid.add(new ArrayList<Cell>)

but the right side is saying that the underlying object can only accept ArrayList<Cell>.

3. The last problem makes me understand subtyping and generics better, specially with collections.

I can replace a List<E> by an ArrayList<E> because the second implements the first interface
but i cannot replace List<List<E>> with ArrayList<ArrayList<E>> because 

The first type allow me to add any implementation of List<E> as an element of the list,
but the second one only restrict me to only add ArrayList<E>.

You cannot use an ArrayList<ArrayList<E>> as you would use a List<List<E>>.

Therefore, **ArrayList<ArrayList<E>>** is not a subtype of **List<List<E>>**
but **ArrayList<E>** is a subtype of **List<E>**


### How is this problem called?

It's called invariance violation attempt.

Invariance means that a type constructor like F<T> accomplish:

A is a subtype of B does NOT imply F(A) is a subtype of F(B) 


### Java's compiler POV

💥 What breaks?
Case A: direct class cast failure

Inside a real JVM implementation, the list stores elements as Object, but expects logical consistency.

Eventually, when retrieved:

ArrayList<Cell> row = grid.get(0); // expected

But actual element is:

LinkedList<Cell>

So when code assumes ArrayList behavior:

random access assumptions (get(i) O(1))
internal structure assumptions
possible downcasts inside library code

👉 boom: ClassCastException

Case B: silent logic corruption (more dangerous)

Even worse: no immediate crash.

Instead:

algorithms assume contiguous storage (ArrayList)
but get linked structure (LinkedList)
performance or invariants break silently

This is called:

type safety violation leading to heap pollution

3. The exact technical term for the failure

If Java allowed it, the situation is called:

💥 Heap pollution

Definition:

A situation where a variable of a parameterized type refers to an object that is not of that parameterized type.

This is exactly what generics prevent.

4. Why Java refuses it at compile time

Because Java’s design goal is:

“No runtime surprises caused by generics”

So it enforces:

invariance
compile-time checks
no structural generic substitution

## Conclusion

Funny enough i only need a ArrayList<ArrayList<Cell>> and not a List<List<Cell>>
So this discussion wwas not necessary, but it was fruitful to me.





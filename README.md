# Friends Expenses Calculator

Can't settle accounts with your friends after party? Tired of complex calculations? Here is the solution!

## Example
Imagine that you spent time with 4 friends (there were You, Alice, Bob, Clark, and Dan). 

- Alice bought pizza for 800 c.u., but Dan hates pizza so he didn't eat it.

- You payed for eatings for everyone and spent 1200 c.u.

- Dan buyed cinema tickets (1000 c.u.) for everyone except Alice, because she joined after cinema.

- Alice also buyed drinks (900 c.u.) but You and Dan couldn't drink because of car driving.

- Bob ordered a taxi for himself, Alice and Clark and spent 400 c.u.. 

How can you settle accounts?

## Input
Input number of people and they names.

Than input number of payments and describe each payment as `<payer's name> <reason> <amount>`. 

If you want to exclude someone from payment, just add `exclude` + `<list of names>`. (`Dan CinemaTickets 1000 exclude Alice`)

If you want to include only someone to payment, just add `<list of names>`. (`Bob Taxi 400 Alice Bob Clark`)
```txt
5
You
Alice
Bob
Clark
Dan
5
Alice Pizza 800 exclude Dan
You Eatings 1200
Dan CinemaTickets 1000 exclude Alice
Alice Drinks 900 exclude You Dan
Bob Taxi 400 Alice Bob Clark
```
## Output

Solution - do 4 card2card transactions. Accounts now are settled!

```txt
Expenses sum = 4300.0 c.u.

You's debt is: -510
Alice's debt is: -826
Bob's debt is: 723
Clark's debt is: 1123
Dan's debt is: -510

Solution: 
Transaction{from=Bob, to=You, amount=510 c.u.}
Transaction{from=Bob, to=Alice, amount=826 c.u.}
Transaction{from=Clark, to=Bob, amount=613 c.u.}
Transaction{from=Clark, to=Dan, amount=510 c.u.}
```

a: func(integer, integer): integer is func(f: integer, s: integer) => 5 * 7 + (s - f);
b is if (5 + 1) > 2 then "Hello" else "World" end;
t: [integer] is [5, 7, 9];
r is t[1];
printer is func(value: integer) do print "Result is ", value return 5 end;
printed_r is printer(r)

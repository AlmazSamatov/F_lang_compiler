lengthOfArray is func(arr: [integer]): integer do
    count is 0

    for i in arr loop
        count := count + 1
    end

    return count
end;

telephone_book is { "John Lord": 1000125, "Ian Gillan": 2200330 };

info is (name is "John", age is 21);

arr is [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

printTuple is func(t:(string,integer)) do
     for e in t loop
        print e, " "
     end
     print "\n"
end;

printArray is func(array: [integer]) do
     for e in array loop
        print e, " "
     end
end;

main is func()
do
     print "Tuple elements: "
     printTuple(info)
     print "Array length is: ", lengthOfArray(arr), "\n"
     print "Array elements are: "
     printArray(arr)

end;

t is main()
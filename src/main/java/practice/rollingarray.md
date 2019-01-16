We need keeping 3 values  in a row.  When a new value is pushed and there are 3 values already, we want to remove one from front and shift the last two values, then push the new one.

How can we do this efficiently?

Keep a length i;
For each new value, just set the value to a[i%3]
the value before last one a[(i-1)%3];
The value second from last a[(i-2)%3];

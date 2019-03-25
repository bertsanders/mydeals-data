# My Deals

## Why my deals?

Every day we remove 100s of SKUs from stores across the company. These items get put on clearance aisles in back corners of our stores. Items that aren't sold are either donated or disposed of, costing our stores and our customers more money. It also impacts our environment.

The clearance areas of our stores frequently hard to find, usually tucked in a back corner. This creates less foot traffic move these items.

It's not much better online or in our apps. You can access some clearance items through the Savings Center, but the Saving's Center is just a jumble of random items that's easy to quickly get fatigued by.

We've priced these items to sell, but we're not doing all we can to get the right eyes on the items to move them quickly. And customer's are less likely to find them while outside of our store.

What if instead of hiding ways to save our customers more money, we used data that we already have to:

* increase foot traffic by attracting customers to the store
* decrease costs to the store from donations or throwing items away
* And reduce our impact on the environment


By combining Item information that we already know with customer data that we already have, such as search and purchase history, we could make recommendations to our customers that would bring them into the store to find items they'd actually like to save more money on.

## How we thought of the idea

As the Store Price Execution team we handle price changes and markdowns for every item in the store. Most of this system is still on legacy systems distributed at each store. In order to get records of this data you would have to log into a store box and query the data.

We had the thought that if this data was available more readily what could we do with it?

## What we made

We ended up making a prototype that could receive pricing events and then send a push notification to a phone based on fairly simple preferences.  

We avoided making any changes to current Walmart systems for this POC. We felt that making quick changes to production systems would be a risk we were not willing to take. However, we are planning to move these legacy systems to the cloud. In that process we could easily build out the missing piece of pushing pricing events to this service.

## Future additions

This hack was meant to just explore the idea of informing customers of markdowns at their local store. However in the future we might even explore more complex notifications using user purchase history. We might push notification to social media platforms to reach others who might not have not have the Walmart App.




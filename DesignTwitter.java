class Twitter {

      HashMap <Integer, Set<Integer>> followed = new HashMap<>();

      HashMap <Integer, List<Tweet>> tweets = new HashMap<>();

      int timeStamp;

      int feedSize = 10;

      private static class Tweet {

          int id;

          int createdAt;

          public Tweet(int id, int createdAt){

              this.id = id;

              this.createdAt = createdAt;

          }

      }

   /** Initialize your data structure here. */

      public Twitter() {

   }

   /** Compose a new tweet. */

      public void postTweet(int userId, int tweetId) {

          // if user is not there in followed table add him

          if(!followed.containsKey(userId)) followed.put(userId, new HashSet<>());

          followed.get(userId).add(userId);

          if(!tweets.containsKey(userId)) tweets.put(userId, new ArrayList <>());

          tweets.get(userId).add(new Tweet(tweetId, timeStamp++));

      }

   /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */

      public List<Integer> getNewsFeed(int userId) {

          PriorityQueue <Tweet> pTweets = new PriorityQueue<>((t1,t2) -> t1.createdAt - t2.createdAt);

          Set<Integer> followeds = followed.get(userId);

          if(followeds != null){

              for(int f : followeds){

                  List<Tweet> fTweets = tweets.get(f);

                  if(fTweets == null) continue;

                  for(Tweet t: fTweets){

                      if(pTweets.size() < feedSize){

                          pTweets.add(t);

                      } else {// where my pq is full

                          if(t.createdAt > pTweets.peek().createdAt){

                              pTweets.poll();

                              pTweets.add(t);

                          }

                      }

                  }

              }

          }

          List<Integer> res = new ArrayList<>();

          while(!pTweets.isEmpty()) res.add(0, pTweets.poll().id);

          return res;

      }

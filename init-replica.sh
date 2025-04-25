#!/bin/bash

# Wait for MongoDB to fully start
echo 'RUNNING init-replica.sh'
sleep 10
# Run the replica set initiation
mongosh --eval 'rs.initiate({_id: "rs0", members: [{_id: 0, host: "mongodb:27017"}]})' --host mongodb --port 27017 -u root -p root
mongosh --eval 'rs.status()' --host mongodb --port 27017 -u root -p root
echo 'init-replica.sh RAN'

# Keep container running (optional: only needed if this is a separate container)
tail -f /dev/null
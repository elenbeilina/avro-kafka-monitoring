printf " -> Adding shark to aquarium \n\n"

curl -X PUT \
 -H "Content-Type: application/json"  \
 http://localhost:8888/aquarium/send-shark
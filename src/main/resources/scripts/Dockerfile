FROM ubuntu:16.04

# Install prerequisites
RUN apt-get update && apt-get install -y curl

# Add bash script
ADD api_caller.sh /api_caller.sh
RUN chmod +x /api_caller.sh

CMD /bin/bash
ENTRYPOINT ["sh", "/api_caller.sh"]

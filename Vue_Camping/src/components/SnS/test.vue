<script>
created : function(){
  fetch('http://localhost:8087/java/hashtag')

  .then(result => result.text())
  .then(result => result.substring(2, result.length-2))

  .then(result => {

    result.replaceAll('","', ' ')
    result.split(' ')
    result.join(' ')
  })
  .then(result => {
    console.log(result)
    this.snsData.push(result)
  })

  .catch(err => console.log(err));
</script>

created : function(){
    fetch('http://localhost:8087/java/hashtag')

    .then(result => result.text())
    .then(result => result.substring(2, result.length-2))
    .then(result => result.replaceAll('","', ' '))
    // .then(result => {
    //   result = result.split('","')
    // console.log(result)})
    // .then(result => result.text())
    .then(result => result.split(' '))
    .then(result => result.join(' '))
    
    .then(result => {
      console.log(result)
      this.snsData.push(result)
    })
    // .catch(err => console.log(err));

  //   fetch('http://localhost:8087/java/snsnickname')
  //   .then(result => result.text())
  //   .then(result => {
  //     console.log(result)
  //   })
    .catch(err => console.log(err));
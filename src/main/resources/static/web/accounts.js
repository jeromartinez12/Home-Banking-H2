const app = Vue.

    createApp({
        data() {
            return {
                accounts: [],
                loans: [],
                clients: [],
                interest: [],
                accountId: [],

                active: [],

                accountType: '',

            }
        },

        created() {
            axios.get('/api/clients/current')
                .then((response) => {
                    this.clients = response.data;
                    this.accounts = this.clients.accounts.sort((a, b) => {
                        return a.id - b.id
                    });
                    this.active = this.accounts.filter(a => a.active == true);
                    this.loans = this.clients.loans;
                    this.interest = this.loans.interest;

                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        methods: {
            newDate(creationDate) {
                creationDate = new Date(creationDate).toLocaleString();
                return creationDate;
            },
            newBalance(balance) {
                balance = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(balance);
                return balance;
            },
            logOut() {
                axios.post('/api/logout')
                    .then(response => location.href = '/web/index.html')
            },

            newAccount() {
                axios.post('/api/clients/current/accounts', `accountType=${this.accountType}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                    .then(response => {
                        swal("The account has been created!", "You will be able to see the account in your list of account", "success", {
                            timer: 4000,
                        })
                    })
                    .catch(error => {
                        swal("Error!", error.response.data, "error", {
                            timer: 6000,
                        })
                    })

            },
            currentDateTime() {
                const time = new Date().getHours();

                if (time >= 6 && time < 12) {
                    return this.welcome = "Good Morning";
                }
                else if (time >= 12 && time < 18) {
                    return this.welcome = "Good Afternoon";
                }
                else if (time >= 18 && time < 22) {
                    return this.welcome = "Good Evening";
                } else {
                    return this.welcome = "Good Night";
                }
            },

            pausedAccount(account) {
                this.accountId = account.id;
                axios.patch('/api/clients/current/accounts/stopaccount', `accountId=${this.accountId}`)
                    .then(response => {
                        swal("The account has been paused!", "If you want to reactivate the account, contact us", "warning", {
                            timer: 4000,

                        })
                            .then(response => window.location.reload())
                    })
                    .catch(error => {
                        swal("Error!", error.response.data, "error", {
                            timer: 6000,
                        })
                    })
            },

        }

    }).mount('#app')